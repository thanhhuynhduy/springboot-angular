import {HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';
import {isPlatformBrowser} from '@angular/common';


@Injectable()

export class UniversalAppInterceptor implements HttpInterceptor {

  constructor(
    @Inject(PLATFORM_ID) private platformId: Object) // Thêm platformId)
  {};

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    if (isPlatformBrowser(this.platformId)) { // Chỉ chạy ở browser
      const authToken = localStorage.getItem("token");
      if (localStorage.getItem("isLoggedIn") === "true") {
        const authReq = req.clone({
          setHeaders: { Authorization: `Bearer ${authToken}` }
        });
        return next.handle(authReq);
      }
    }
    return next.handle(req);
  }
}
