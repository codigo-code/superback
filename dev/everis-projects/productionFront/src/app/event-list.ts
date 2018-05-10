import { Component, OnInit } from "@angular/core";
import { ShareService } from "./share.services";

declare let toastr;

@Component({
    selector:'event-list',
    template:`<h1>Hola mundo</h1>
            <event-item #variablex  (eventClick)="handlerEventClick($event)" [event]="event1"></event-item>

            <h3>{{variablex.saludar}}</h3>
            <button (click)="variablex.logFoo()">Fooo</button>
            <div *ngFor="let e of eventis"> <h2>{{e.name}}</h2></div>
<br>
            <div *ngFor="let i of event1"> <h2>{{i.name}}</h2> <button (click)="verTostada(i.name)">ver nombre</button></div>
    `

})
export class EventList implements OnInit{

event1:any;

    constructor(private eventService:ShareService){
    //    this.event1=eventService;
    }

ngOnInit(){
    this.event1=this.eventService.getEvents();
}
    
    prueba ='Hola mundo';

    
    handlerEventClick(data){
        console.log(data);
    }

    verTostada(nombre){
        //console.log(toastr.success);
        toastr.info("nombre");
    }
}
