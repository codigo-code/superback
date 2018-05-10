import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { EventList } from "./event-list";
import { EventItem } from "./event.item";

import { AppComponent } from './app.component';
import { ShareService } from './share.services';
import { appRoutes } from '../../routers';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    AppComponent,
    EventList,
    EventItem
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule ,
    RouterModule.forRoot(appRoutes)       
  ],
  providers: [ShareService],
  bootstrap: [AppComponent]
})
export class AppModule { }
