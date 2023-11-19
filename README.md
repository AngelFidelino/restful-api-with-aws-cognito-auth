Restful API with AWS Cognito Auth

This application exposes endpoints through an API Gateway to obtain information from a h2 in-memory db.
These endpoints are secured using cognito user pool, and the database configuration are obtained from a Secret Manager configuration.

Items included
* AWS Lambda
* AWS Cognito 
* Aws Secret Manager
* SAM (Cloudformation)

Steps to run this project:
To build the Cloudformation template: sam build
To run the app locally: sam local start-api
To deploy the app to aws: sam deploy --guided 
To update the stack: sam deploy --stack-name [stack-name] --capabilities CAPABILITY_IAM

To run it locally with "sam local start-api" make sure you have docker installed, and you are login with docker login to pull the required images from public.ecr.aws. If you have problems do the following:
    - Write next command to authenticate the Docker CLI to your default registry. That way, the docker command can push and pull images with Amazon ECR: aws ecr-public get-login-password --region us-east-1 | docker login --username AWS --password-stdin public.ecr.aws
    - Also ensure that you have configured your AWS CLI to interact with AWS (See "AWS CLI configuration basics" https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-configure.html)

Spring Boot supported: 2.2.x, 2.3.x, 2.4.x, 2.5.x, 2.6.x and 2.7.x.
Configuration:
    * SecretManager: dev/myApp/h2 (must include username and password properties)
    * Cognito UserPool
       The UserPool information must be updated in the property file following: https://cognito-idp.[AWS REGION].amazonaws.com/[USER_POOL_ID] 




Revisar error en spring security
https://www.baeldung.com/spring-security-oauth-cognito
https://stackoverflow.com/questions/48327369/amazon-cognito-oauth2-with-spring-security

<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
        </dependency>
