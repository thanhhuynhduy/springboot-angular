import {HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';


@Injectable()

export class UniversalAppInterceptor implements HttpInterceptor {

  constructor() {
  };

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const authToken = localStorage.getItem("token");
    if (localStorage.getItem("isLoggedIn") == "true") {
      const authReq = req.clone({
        url: req.url,
        setHeaders: {
          Authorization: `Bearer ${authToken}`
        }
      });
      return next.handle(authReq);
    }
    return next.handle(req);
  }
}
