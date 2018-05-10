import { Routes } from "@angular/router";
import { EventList } from "./src/app/event-list";
import { EventItem } from "./src/app/event.item";


export const appRoutes: Routes=[
    {path: 'events', component: EventList},
    {path:'event/:id', component:EventItem},
    {path:'',redirectTo:'/events',pathMatch:'full'}

]