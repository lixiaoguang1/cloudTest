import { NgModule } from "@angular/core";
import { HttpModule, JsonpModule} from "@angular/http";
import { Model } from "./repository.model";
import { Product } from "./product.model";
//import { StaticDataSource } from "./static.datasource";
import { RestDataSource,REST_URL } from "./rest.datasource";

@NgModule({
    imports: [ HttpModule, JsonpModule ],
	providers: [ Model,RestDataSource,
      { provide: REST_URL, useValue: `http://${location.hostname}:3500/products`}
	]
})

export class ModelModule { }