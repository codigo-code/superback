import { Component,Input, Output,EventEmitter  } from "@angular/core";

@Component({
    selector:'event-item',
    template:`<div class="pepe"> <h2>{{event.name}}</h2>
            <button (click)="handleClickMe()"> comela</button>
            </div>
    `,
    styles:[`
            .pepe{background-color:red}
          `]
})
export class EventItem{

    @Input() event:any;

    @Output() eventClick=  new EventEmitter();


    saludar:string="hola desde un string";

    handleClickMe(){
        this.eventClick.emit(this.event.name);
    }

    logFoo(){
        console.log("fooooo");
    }
}