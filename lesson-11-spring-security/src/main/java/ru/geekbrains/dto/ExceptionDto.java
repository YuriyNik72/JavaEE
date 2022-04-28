package ru.geekbrains.dto;

public class ExceptionDto {

    private String notFoundException;

    private String illegalArgumentException;

    private String sqlException;

    public ExceptionDto(String notFoundException, String illegalArgumentException, String sqlException) {
        this.notFoundException = notFoundException;
        this.illegalArgumentException = illegalArgumentException;
        this.sqlException = sqlException;
    }

    public ExceptionDto() {
    }

    public String getNotFoundException() {
        return notFoundException;
    }

    public void setNotFoundException(String notFoundException) {
        this.notFoundException = notFoundException;
    }

    public String getIllegalArgumentException() {
        return illegalArgumentException;
    }

    public void setIllegalArgumentException(String illegalArgumentException) {
        this.illegalArgumentException = illegalArgumentException;
    }

    public String getSqlException() {
        return sqlException;
    }

    public void setSqlException(String sqlException) {
        this.sqlException = sqlException;
    }
}
