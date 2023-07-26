package com.olsh4u.epam.controllers.command.postcommand.testimpl;


import com.olsh4u.epam.controllers.command.Command;
import com.olsh4u.epam.controllers.command.CommandResponse;
import com.olsh4u.epam.controllers.command.RoutingType;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.Ability;
import com.olsh4u.epam.models.Alien;
import com.olsh4u.epam.models.Planet;
import com.olsh4u.epam.service.factory.ServiceFactory;
import com.olsh4u.epam.utils.RoutingUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.sql.Date;
import java.util.*;

import static com.olsh4u.epam.utils.Constants.*;


/**
 * Command to save {@link com.olsh4u.epam.models.Alien}.
 *
 * @see Command
 * @see com.olsh4u.epam.controllers.command.postcommand.PostCommandProvider
 */
public class SaveAlienPostCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);

    /**
     * Command to save {@link com.olsh4u.epam.models.Alien}. Handles both change and creation requests.
     * To work with downloading files use {@link org.apache.commons.fileupload.FileUpload}.
     * A folder for saving files declared in context.
     * If the file was not transferred, the default image is set if it is not present.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the POST request
     * @see RoutingUtils
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        try {
            String alienId = req.getParameter(PARAMETER_ID) != null ? req.getParameter(PARAMETER_ID) : String.valueOf(0);
            ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
            fileUpload.setFileSizeMax(MAX_USER_AVATAR_SIZE);
            List<FileItem> multiFiles = fileUpload.parseRequest(req);
            Alien alien = new Alien().Builder()
                    .withId(Integer.parseInt(alienId))
                    .withGenres(new ArrayList<>())
                    .withCountry(new Ability())
                    .withStudio(new Planet())
                    .build();

            if (!alienId.equals("0")) {
                alien = ServiceFactory.getInstance().getAlienService().findById(alienId);
                alien.setSourceList(new ArrayList<>());
            }

            for (FileItem item : multiFiles
            ) {
                if (item.isFormField()) {
                    processFormField(item, alien);
                } else {
                    processUploadedFile(item, alien, req);
                }
            }
            ServiceFactory.getInstance().getAlienService().save(alien);
            return new CommandResponse(RoutingType.REDIRECT, req.getHeader(HEADER_REFERER), req, resp);
        } catch (ServiceException e) {
            if (e.getCode() == ServiceException.BAD_REQUEST && e.getErrors() != null) {
                req.getSession().setAttribute(ATTRIBUTE_ALIEN_PROBLEM, e.getErrors());
                return new CommandResponse(RoutingType.REDIRECT, req.getHeader(HEADER_REFERER), req, resp);
            } else {
                logger.error(e);
                return RoutingUtils.routingErrorPage(req, resp, e.getCode());
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            logger.error("Max file size", e);
            return new CommandResponse(RoutingType.REDIRECT, req.getHeader(HEADER_REFERER), req, resp);
        } catch (Exception e) {
            logger.error("File cannot write", e);
            return RoutingUtils.routingErrorPage(req, resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Method for processing fields. For reading fields the encoding is used UTF-8.
     *
     * @param item   the {@link FileItem}
     * @param alien the {@link Alien}
     * @throws UnsupportedEncodingException if the requested character encoding is not available
     * @throws ServiceException             if the date is incorrect
     */
    private void processFormField(final FileItem item,
                                  final Alien alien) throws UnsupportedEncodingException, ServiceException {
        switch (item.getFieldName()) {
            case PARAMETER_NAME:
                alien.setName(item.getString(ENCODING_UTF_8));
                break;
            case PARAMETER_DESCRIPTION:
                alien.setDescription(item.getString(ENCODING_UTF_8));
                break;
            case PARAMETER_FIRST_APPEARANCE:
                try {
                    alien.setFirstAppearance(Date.valueOf(item.getString()));
                    break;
                } catch (IllegalArgumentException e) {
                    Map<String, String> errors = new HashMap<>();
                    errors.put(ATTRIBUTE_SERIAL_PUBLICATION_DATE_PROBLEM, "incorrectData");
                    throw new ServiceException("Incorrect data", HttpServletResponse.SC_BAD_REQUEST, errors);
                }
            case PARAMETER_GENRE:
                Genre genre = new Genre();
                genre.setId(Integer.parseInt(item.getString()));
                serial.getGenres().add(genre);
                break;
            case PARAMETER_COUNTRY:
                serial.getCountry().setId(Integer.parseInt(item.getString()));
                break;
            case PARAMETER_STUDIO:
                serial.getStudio().setId(Integer.parseInt(item.getString()));
                break;
            default:
                logger.error("Not found field");
                break;
        }
    }

    /**
     * Method for processing files.
     * If the picture was not transferred and the series field is empty then the default picture is set.
     * If the picture was not transferred and the series field is not empty, processing is skipped.
     * In another case, the picture was transferred.
     * The file is saved in the folder for files and the name of the picture is written in the value of the series field.
     * A folder for saving files declared in context.
     *
     * @param item   the {@link FileItem}
     * @param serial the {@link Serial}
     * @param req    HttpServletRequest
     * @throws Exception if an error occurred while processing the file
     */
    private void processUploadedFile(final FileItem item, final Serial serial,
                                     final HttpServletRequest req) throws Exception {
        String fileName = UUID.randomUUID().toString() + item.getName();
        switch (item.getFieldName()) {
            case PARAMETER_LOGO:
                if (item.getName().equals("") && serial.getLogo() == null) {
                    serial.setLogo(DEFAULT_IMG_NAME);
                    break;
                } else if (item.getName().equals("") && serial.getLogo() != null) {
                    break;
                } else {
                    String filePath = req.getServletContext().getRealPath("") + "img" + File.separator
                            + fileName;
                    File logo = new File(filePath);
                    item.write(logo);
                    if (serial.getLogo() != null && !serial.getLogo().equals(DEFAULT_IMG_NAME)) {
                        File oldLogo = new File(req.getServletContext().getRealPath("") + "img" + File.separator
                                + serial.getLogo());
                        if (oldLogo.exists()) {
                            Files.delete(oldLogo.toPath());
                        }
                    }

                    serial.setLogo(logo.getName());
                    break;
                }
            case PARAMETER_FULL_LOGO:
                if (item.getName().equals("") && serial.getFullLogo() == null) {
                    serial.setFullLogo(DEFAULT_IMG_NAME);
                    break;
                } else if (item.getName().equals("") && serial.getFullLogo() != null) {
                    break;
                } else {
                    String filePath = req.getServletContext().getRealPath("") + "img" + File.separator + fileName;
                    File fullLogo = new File(filePath);
                    item.write(fullLogo);
                    if (serial.getFullLogo() != null && !serial.getFullLogo().equals(DEFAULT_IMG_NAME)) {
                        File oldFullLogo = new File(req.getServletContext().getRealPath("")
                                + "img" + File.separator + serial.getFullLogo());
                        if (oldFullLogo.exists()) {
                            Files.delete(oldFullLogo.toPath());
                        }
                    }
                    serial.setFullLogo(fullLogo.getName());
                    break;
                }
            default:
                logger.error("Not found field");
                break;
        }
    }
}
