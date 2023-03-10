package com.example.library.model.enums;

import java.text.MessageFormat;

public enum EExceptionMessage {

    ////////////////////////////////////////////////////////////////////////////////////////////
    // GENERAL EXCEPTION MESSAGE
    ////////////////////////////////////////////////////////////////////////////////////////////
    ID_NOT_FOUND("ID NO ENCONTRADO"),
    ID_ALREADY_EXISTS("EL ID YA EXISTE"),
    PARAM_NOT_FOUND("PARÁMETRO NO ENCONTRADO"),
    EMAIL_ALREADY_EXISTS("EL EMAIL YA EXISTE"),
    INCORRECT_USERNAME_OR_PASSWORD("USUARIO O CONTRASEÑA INCORRECTA"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // ADMIN EXCEPTION MESSAGE
    ////////////////////////////////////////////////////////////////////////////////////////////
    THE_ADMIN_NAME_CANNOT_BE_EMPTY_OR_BE_NULL("EL NOMBRE DEL ADMIN NO PUEDE ESTAR VACÍO O SER NULO"),
    THE_ADMIN_SURNAME_CANNOT_BE_EMPTY_OR_BE_NULL("EL APELLIDO DEL ADMIN NO PUEDE ESTAR VACÍO O SER NULO"),
    THE_ADMIN_EMAIL_CANNOT_BE_EMPTY_OR_BE_NULL("EL EMAIL DEL ADMIN NO PUEDE ESTAR VACÍO O SER NULO"),
    THE_ADMIN_PASSWORD_CANNOT_BE_EMPTY_OR_BE_NULL("LA CONTRASEÑA DEL ADMIN NO PUEDE ESTAR VACÍA O SER NULA"),
    THE_ADMIN_PASSWORD_CANNOT_BE_SHORTER_THAN_6_CHARACTERS("LA CONTRASEÑA DEL ADMIN NO PUEDE SER MENOR A 6 CARACTERES"),
    THE_ENTERED_PASSWORDS_DO_NOT_MATCH("LAS CONTRASEÑAS INGRESADAS NO COINCIDEN"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // AUTHOR EXCEPTION MESSAGE
    ////////////////////////////////////////////////////////////////////////////////////////////
    THE_AUTHOR_NAME_CANNOT_BE_EMPTY_OR_BE_NULL("EL NOMBRE DEL AUTOR NO PUEDE ESTAR VACÍO O SER NULO"),
    THE_AUTHOR_SURNAME_CANNOT_BE_EMPTY_OR_BE_NULL("EL APELLIDO DEL AUTOR NO PUEDE ESTAR VACÍO O SER NULO"),
    AUTHOR_NOT_FOUND("AUTOR NO ENCONTRADO"),
    ERROR_WHEN_DISPLAYING_A_LIST_OF_ALL_AUTHORS("ERROR AL MOSTAR UNA LISTA DE TODOS LOS AUTORES"),
    ERROR_WHEN_DISPLAYING_ACTIVE_AUTHORS("ERROR AL MOSTAR UNA LISTA DE AUTORES ACTIVOS"),
    ERROR_WHEN_DISPLAYING_INACTIVE_AUTHORS("ERROR AL MOSTAR UNA LISTA DE AUTORES INACTIVOS"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // EDITORIAL EXCEPTION MESSAGE
    ////////////////////////////////////////////////////////////////////////////////////////////
    THE_EDITORIAL_NAME_CANNOT_BE_EMPTY_OR_BE_NULL("EL NOMBRE DE LA EDITORIAL NO PUEDE ESTAR VACÍO O SER NULO"),
    EDITORIAL_NOT_FOUND("EDITORIAL NO ENCONTRADA"),
    ERROR_WHEN_DISPLAYING_A_LIST_OF_ALL_EDITORIALS("ERROR AL MOSTAR UNA LISTA DE TODOS LAS EDITORIALES"),
    ERROR_WHEN_DISPLAYING_ACTIVE_EDITORIALS("ERROR AL MOSTAR UNA LISTA DE EDITORIALES ACTIVAS"),
    ERROR_WHEN_DISPLAYING_INACTIVE_EDITORIALS("ERROR AL MOSTAR UNA LISTA DE EDITORIALES INACTIVAS"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // BOOK EXCEPTION MESSAGE
    ////////////////////////////////////////////////////////////////////////////////////////////
    THE_BOOK_TITLE_CANNOT_BE_EMPTY_OR_BE_NULL("EL TÍTULO DEL LIBRO NO PUEDE ESTAR VACÍO O SER NULO"),
    THE_BOOK_YEAR_CANNOT_BE_EMPTY_OR_BE_NULL("EL AÑO DE PUBLICACIÓN DEL LIBRO NO PUEDE ESTAR VACÍO O SER NULO"),
    THE_YEAR_OF_PUBLICATION_CANNOT_BE_OLDER_THAN_THE_CURRENT_DATE("EL AÑO DE PUBLICACIÓN NO PUEDE SER MAYOR A LA FECHA ACTUAL"),
    BOOK_NOT_FOUND("LIBRO NO ENCONTRADO"),
    ERROR_WHEN_DISPLAYING_A_LIST_OF_ALL_BOOKS("ERROR AL MOSTAR UNA LISTA DE TODOS LOS LOS LIBROS"),
    ERROR_WHEN_DISPLAYING_ACTIVE_BOOKS("ERROR AL MOSTAR UNA LISTA DE LIBROS ACTIVOS"),
    ERROR_WHEN_DISPLAYING_INACTIVE_BOOKS("ERROR AL MOSTAR UNA LISTA DE LIBROS INACTIVOS"),
    THE_NUMBER_OF_ITEMS_CANNOT_BE_EMPTY_OR_LESS_THAN_0("LA CANTIDAD DE EJEMPLARES NO PUEDE ESTAR VACÍA O SER MENOR A 0"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // LOAN EXCEPTION MESSAGE
    ////////////////////////////////////////////////////////////////////////////////////////////
    THE_NUMBER_OF_COPIES_TO_BE_LOANED_CANNOT_BE_EMPTY("LA CANTIDAD DE EJEMPLARES A PRESTAR NO PUEDE ESTAR VACÍA"),
    THE_NUMBER_OF_COPIES_TO_BE_LOANED_MUST_BE_GREATER_THAN_0("LA CANTIDAD DE EJEMPLARES A PRESTAR TIENE QUE SER MAYOR A 0"),
    LOAN_NOT_FOUND("PRÉSTAMO NO ENCONTRADO"),
    THERE_ARE_NO_BOOKS_AVAILABLE_FOR_LOAN("NO QUEDAN LIBROS DISPONIBLES PARA REALIZAR EL PRÉSTAMO"),
    THE_LOAN_HAS_BEEN_EXTENDED("EL PRÉSTAMO YA FUE EXTENDIDO"),
    ERROR_WHEN_DISPLAYING_A_LIST_OF_ALL_LOANS("ERROR AL MOSTAR UNA LISTA DE TODOS LOS PRÉSTAMOS"),
    ERROR_WHEN_DISPLAYING_ACTIVE_LOANS("ERROR AL MOSTAR UNA LISTA DE PRÉSTAMOS ACTIVOS"),
    ERROR_WHEN_DISPLAYING_INACTIVE_LOANS("ERROR AL MOSTAR UNA LISTA DE PRÉSTAMOS INACTIVOS"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // CLIENT EXCEPTION MESSAGE
    ////////////////////////////////////////////////////////////////////////////////////////////
    CLIENT_NOT_FOUND("CLIENTE NO ENCONTRADO"),
    THE_CLIENT_NAME_CANNOT_BE_EMPTY_OR_BE_NULL("EL NOMBRE DEL CLIENTE NO PUEDE ESTAR VACÍO O SER NULO"),
    THE_CLIENT_SURNAME_CANNOT_BE_EMPTY_OR_BE_NULL("EL APELLIDO DEL CLIENTE NO PUEDE ESTAR VACÍO O SER NULO"),
    THE_CLIENT_DOCUMENT_CANNOT_BE_EMPTY_OR_BE_NULL("EL DOCUMENTO DEL CLIENTE NO PUEDE ESTAR VACÍO O SER NULO"),
    THE_DOCUMENT_MUST_CONTAIN_8_NUMBERS("EL NÚMERO DE DOCUMENTO TIENE QUE CONTENER 8 NÚMEROS"),
    THE_TELEPHONE_NUMBER_CANNOT_BE_EMPTY_OR_BE_NULL("EL NÚMERO DE TELÉFONO NO PUEDE ESTAR VACÍO O SER NULO"),
    THE_CLIENT_EMAIL_CANNOT_BE_EMPTY_OR_BE_NULL("EL EMAIL DEL CLIENTE NO PUEDE ESTAR VACÍO O SER NULO"),
    THE_CLIENT_PASSWORD_CANNOT_BE_EMPTY_OR_BE_NULL("LA CONTRASEÑA DEL CLIENTE NO PUEDE ESTAR VACÍA O SER NULA"),
    THE_CLIENT_OLD_PASSWORD_CANNOT_BE_EMPTY_OR_BE_NULL("LA ANTIGUA CONTRASEÑA DEL CLIENTE NO PUEDE ESTAR VACÍA O SER NULA"),
    THE_CLIENT_PASSWORD_CANNOT_BE_SHORTER_THAN_6_CHARACTERS("LA CONTRASEÑA DEL CLIENTE NO PUEDE SER MENOR A 6 CARACTERES"),
    ERROR_WHEN_DISPLAYING_A_LIST_OF_ALL_CLIENTS("ERROR AL MOSTAR UNA LISTA DE TODOS LOS CLIENTES"),
    ERROR_WHEN_DISPLAYING_ACTIVE_CLIENTS("ERROR AL MOSTAR UNA LISTA DE CLIENTES ACTIVOS"),
    ERROR_WHEN_DISPLAYING_INACTIVE_CLIENTS("ERROR AL MOSTAR UNA LISTA DE CLIENTES INACTIVOS"),
    WRONG_PASSWORD("LA ANTIGUA CONTRASEÑA ES INCORRECTA"),
    DOCUMENT_ALREADY_EXISTS("EL NÚMERO DE DOCUMENTO YA EXISTE"),
    THE_CLIENT_HAS_SUCCESSFULLY_REGISTERED("EL CLIENTE SE REGISTRÓ EXITOSAMENTE");

    private final String messageString;

    EExceptionMessage(String messageString) {
        this.messageString = messageString;
    }

    public String getMessage() {
        return messageString;
    }

    public String getMessage(String stringObject) {
        return MessageFormat.format(messageString, stringObject);
    }

    @Override
    public String toString() {
        return messageString;
    }

    public static EExceptionMessage typeOrValue(String value) {
        for (EExceptionMessage type : EExceptionMessage.class.getEnumConstants()) {
            if (type.toString().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("ENUM MESSAGE NOT FOUND");
    }
}