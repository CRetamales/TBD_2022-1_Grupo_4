<template>
<body>
    <Navbar/>
    <div class="wrapper fadeInDown scrollDiv col-sm" style="background-color: #4f3185">
        
        <div v-for="(item, index) in items_emergencia" :key="index" id="formTareasID" style="background-color: #169691">
        <h1>{{item.nombre}} id: {{param}}</h1>

        <!-- Mapa -->  
        <!-- Descripcion -->
        <p>
            {{item.descripcion}}
        </p>

        <!-- Botones -->

        <div class="contenedorDos">
            <div>
            <a href="#">
                <button type="button" class="btn btn-primary btn-lg">
                Aceptar
                </button>
            </a>
            </div>

            <div>
            <a href="#">
                <button type="button" class="btn btn-primary btn-lg">
                Rechazar
                </button>
            </a>
            </div>
        </div>
    </div>

    <!-- Botones Emergencia -->
    <div class ="col-sm" style="padding-top:30px">
        
        <div  v-for="(item, index) in items_tarea" :key="index">
            <div>
                <h1> </h1>
                <a v-bind:href="'../TareaId/'+item.id">
                <button class="botonDos"><span>{{item.nombre}}</span></button>
                </a>
            </div>
        </div>
        <div>
            <h1> </h1>
            <a href="../Tareas">
            <button class="botonDos"><span>Mas Tareas</span></button>
            </a>
        </div>
    </div>
    <div class ="col-sm" style="padding-top:30px">
        <div>
            <a href="/NuevaTarea">
                <button type="button" class="btn btn-success">Nueva tarea</button>
            </a>
        </div>
    </div>
    </div>
</body>
</template>

<script>
//Importaciones
import 'leaflet/dist/leaflet'; //librería leaflet
import 'leaflet/dist/leaflet.css'; //css leaflet
var icon = require('leaflet/dist/images/marker-icon.png'); //ícono de marcadores
//Se crea objeto ícono con el marcador
var LeafIcon = L.Icon.extend({
          options: {iconSize:[25, 41], iconAnchor:[12, 41], popupAnchor: [-3, -41]}
      });
var myIcon = new LeafIcon({iconUrl: icon});

//Libreria axios
import axios from 'axios';
export default {
    name: 'emergenciaId',
    data:function(){
        return{
            param:this.$route.params.id,
            items_emergencia:[],
            items_tarea:[],
            latitude:null, //Datos de nuevo punto
            longitude:null,
            name:'',
            points:[], //colección de puntos cargados de la BD
            message:'', 
            mymap:null, //objeto de mapa(DIV)
            selected:'',
            //regiones:[],
        }
    },
    methods:{
        //Se obtiene la emergencia con el id
        getEmergenciaId:function(){
            axios.get('/emergencia/getById/'+this.param)
            .then(response => {
                this.items_emergencia = response.data;
            })
            .catch(error => {
                console.log(error);
            })
        },
        //Se obtiene las tareas de la emergencia
        getTareasEmergencia:function(){
            axios.get('/tarea/getTareaByIdEmergencia/'+this.param)
            .then(response => {
                this.items_tarea = response.data;
            })
            .catch(error => {
                console.log(error);
            })
        },
 
    },
    mounted:function(){

        //Se obtiene la emergencia con el id
        axios.get('/emergencia/getById/'+this.param)
        .then(response => {
            this.items_emergencia = response.data;
        })
        .catch(error => {
            console.log(error);
        })
        //Se obtiene las tareas de la emergencia
        axios.get('/tarea/getTareaByIdEmergencia/'+this.param)
        .then(response => {
            this.items_tarea = response.data;
        })
        .catch(error => {
            console.log(error);
        })
    },
}
</script>
<style>
/* Estilos necesarios para definir el objeto de mapa */
#mapid { 
  height: 400px; 
  width:600px;
}
</style>

