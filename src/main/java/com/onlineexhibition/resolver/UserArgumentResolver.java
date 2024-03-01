package com.onlineexhibition.resolver;

import com.onlineexhibition.model.User;
import com.onlineexhibition.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
   @Autowired
   private UserRepository userRepository;

   @Override
   public boolean supportsParameter(MethodParameter parameter) {
      return User.class.equals(parameter.getParameterType());
   }

   @Override
   public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                 NativeWebRequest webRequest,
                                 WebDataBinderFactory binderFactory) throws Exception {
      //      HttpServletRequest servletRequest = (HttpServletRequest) webRequest.getNativeRequest();
      String token = webRequest.getHeader("X-API-TOKEN");
      System.out.println("TOKENS: " + token);
      if (token == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");

      //TODO need add token to field user table
//      User user = userRepository.findFirstByToken(token)
//              .orElseThrow(
//                      () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is unauthorized"));
//
//      if (user.getTokenExpiredAt() < System.currentTimeMillis()) {
//         throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is unauthorized");
//      }
//
//      return user;
      return null;
   }
}
