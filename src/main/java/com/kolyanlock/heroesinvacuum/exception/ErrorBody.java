package com.kolyanlock.heroesinvacuum.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public class ErrorBody {

    private LocalDateTime timestamp;

    private int status;

    private String error;

    private String exception;

    private String message;

    private String path;
}
