package in.backend.core.auth.infrastrcutrue;


import in.backend.global.exception.GlobalException;
import in.backend.global.exception.GlobalExceptionCode;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient.ResponseSpec.ErrorHandler;

@Component
public class Status5xxErrorHandler implements ErrorHandler {
    @Override
    public void handle(HttpRequest request, ClientHttpResponse response) {
        throw new GlobalException(GlobalExceptionCode.INVALID_OAUTH_SERVER);
    }
}
