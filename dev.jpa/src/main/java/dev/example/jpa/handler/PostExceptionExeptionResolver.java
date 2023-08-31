package dev.example.jpa.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.example.jpa.exception.BaseException;
import dev.example.jpa.exception.ErrorResponseDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PostExceptionExeptionResolver extends AbstractHandlerExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.debug(ex.getMessage());
        if(ex instanceof BaseException){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try{
                response.getOutputStream().print(
                        new ObjectMapper().writeValueAsString(
                                new ErrorResponseDto("asdasd"+ex.getMessage())
                        )
                );
              return new ModelAndView(ex.getMessage());

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
