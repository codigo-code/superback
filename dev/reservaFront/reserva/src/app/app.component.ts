import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  nombre: string = "amilcar";

  //nombre de la variable: tipo = valor
  edad: number = 27;
  reservaResp:any={};
  

  constructor(private http: HttpClient) {

  }

  

  obj: any = {
    nombre: 'kevin',
    apellido: 'peroti',
    edad: 26
  };

  
  
  direccion: string = this.getDireccion();

  getDireccion() {

    this.nombre = "UTN BA";
    var direccion = "Medrano 951";

    
    return this.nombre + " " + direccion;
  }


  getReservas(id:number){
  
    
   var res = this.http.get('http://localhost:8080/getBar/'+id).subscribe(res =>{
     
      var resultado:any = res;

      console.log(resultado.value.nombre);
      this.reservaResp.nombre =resultado.value.nombre;

      this.reservaResp.precio =resultado.value.precio;
      this.reservaResp.id =resultado.value.id;
      
      
   },err=>{
      console.log("no se encontro el id para el bar solicitado");
   });

   
   return res; 
  }

  callBar(v){
    
         this.getReservas(v.barnombre);
  }

}

interface reservaRespuesta{
  id:number;
  nombre:string;
  precio:number;
}
