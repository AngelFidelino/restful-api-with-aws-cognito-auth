package org.alfr.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AwsSecret {
    private String username;
    private String password;
    private String host;
    private String engine;
    private String port;
    protected String dbInstanceIdentifier;
}
