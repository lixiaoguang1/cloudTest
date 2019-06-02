import { NgModule } from "@angular/core";
import { Model } from "./repository.model";
import { Product } from "./product.model";
import { StaticDataSource } from "./static.datasource";

@NgModule({
	providers: [ Model,StaticDataSource ]
})

export class ModelModule { }