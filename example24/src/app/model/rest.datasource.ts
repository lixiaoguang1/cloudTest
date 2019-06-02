import { Injectable,Inject,InjectionToken } from "@angular/core";
import { Http } from "@angular/http";
import { Observable } from "rxjs";
import { map } from "rxjs/operators"; 
import { Product } from "./product.model";

export const REST_URL =new InjectionToken("rest_url");
@Injectable()
export class RestDataSource{
	constructor(private http: Http,@Inject(REST_URL) private url:string){ }
	getData(): Observable<Product[]>{
	  return this.http.get(this.url).pipe(map(response => response.json()));
	}

	saveProduct(product: Product): Observable<Product> {
      return this.http.post(this.url,product)
        .pipe(map(response => response.json()));
	}

	updateProduct(product: Product): Observable<Product> {
	  return this.http.put(`${this.url}/${product.id}`,product)
	  .pipe(map(response => response.json()));

	}

	deleteProduct(id:number): Observable<Product> {
	  return this.http.delete(`${this.url}/${id}`)
	  .pipe(map(response => response.json()));
	}
}