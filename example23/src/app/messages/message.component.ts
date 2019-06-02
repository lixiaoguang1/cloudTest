import { Component } from "@angular/core";
import { MessageService } from "./message.service";
import { Message } from "./message.model";
import { Observable } from "rxjs";

@Component({
	selector: "paMessage",
	templateUrl: "message.component.html"
})

export class MessageComponent {
	lastMessage: Message;
	constructor(messageService: MessageService){
	   messageService.messages.subscribe(m => this.lastMessage = m);
	}
}