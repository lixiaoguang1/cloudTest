//form.component.ts
import { Component,Inject } from "@angular/core";
import { NgForm } from "@angular/forms";
import { Product } from "../model/product.model";
import { Model } from "../model/repository.model";
import { MODES, SharedState,SHARED_STATE } from "./sharedState.model";
import { Observable} from "rxjs";
import { filter,map,distinctUntilChanged, skipWhile } from "rxjs/operators";


@Component({
	selector: "paForm",
	templateUrl: "form.component.html",
	styleUrls:["form.component.css"]
})

export class FormComponent {

	product: Product = new Product();
	constructor(private model: Model, 
	            @Inject(SHARED_STATE)private stateEvents: Observable<SharedState> ){
        /*stateEvents
            .pipe(
                //RxJS 参考文档 https://www.npmjs.com/package/rxjs
                 filter(state => state.id !=3),
                  map(state => new SharedState(state.mode,state.id == 5? 1: state.id))
                 )
            .subscribe((update) => {
	            this.product =new Product();
	            if(update.id != undefined){
	               Object.assign(this.product,this.model.getProduct(update.id));
            }
            
            this.editing = update.mode == MODES.EDIT;
        });*/
        /*stateEvents
            .pipe(
                //RxJS 参考文档 https://www.npmjs.com/package/rxjs
                 filter(state => state.id !=3),
                 map(state => state.mode ==MODES.EDIT ?state.id : -1),
                 distinctUntilChanged()
                 )
            .subscribe((id) => {
                this.editing =id !=-1;
	            this.product =new Product();
	            if(id != -1){
	               Object.assign(this.product,this.model.getProduct(id));
            }
        });*/
        stateEvents
            .pipe(
                //RxJS 参考文档 https://www.npmjs.com/package/rxjs
                 distinctUntilChanged((firstState,secondState) =>
                   firstState.mode ==secondState.mode && firstState.id == secondState.id),
                  skipWhile(state => state.mode ==MODES.EDIT)
                )
            .subscribe((update) => {
	            this.product =new Product();
	            if(update.id != undefined){
	               Object.assign(this.product,this.model.getProduct(update.id));
            }
            
            this.editing = update.mode == MODES.EDIT;
        });

	 }

	editing: boolean = false;

	submitForm(form: NgForm) {
	  if(form.valid) {
         this.model.saveProduct(this.product);
         this.product = new Product();
         form.reset();
	  }
	}

	resetForm(){
	  this.product =  new Product();
	}

	/*
	 //不建议使用这种方法
	 ngDoCheck(){
	  if(this.lastId != this.state.id){
	     this.product = new Product();
	     if(this.state.mode == MODES.EDIT){
	       Object.assign(this.product,this.model.getProduct(this.state.id));
	     }
	     this.lastId = this.state.id;
	  }
	}*/
}
