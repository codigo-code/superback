import { Injectable } from "@angular/core";

@Injectable()
export class ShareService{


    getEvents(){
        return EVENTS;
    }

    getEvenet(id:number){
        //=== es identico , == es igual 
        return EVENTS.find(e=> e.id===id);
    }

}

const EVENTS=[{
    id:1,
    name:"berto",
    lastname:"peroncho"

},{
    id:2,
    name:"bertao",
    lastname:"peroncha"
    
}]