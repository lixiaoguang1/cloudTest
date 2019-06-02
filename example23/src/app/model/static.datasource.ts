import { Injectable } from "@angular/core";
import { Product } from "./product.model";

@Injectable()
export class StaticDataSource{
	private data: Product[];
	constructor(){
	  this.data = new Array<Product>(
        new Product(1,"Kayak","WaterSports",275),
        new Product(2,"LifeJacket","WaterSports",48.95),
        new Product(3,"Soccer Ball","Soccer",19.50),
        new Product(4,"Corner Flags","Soccer",34.95),
        new Product(5,"Thinking Cap","Chess",16)
	  );
	}

	getData(): Product[]{
	   return this.data;
	}
}