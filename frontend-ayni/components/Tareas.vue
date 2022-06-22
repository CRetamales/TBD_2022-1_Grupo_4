<template>
<body>
  <div class ="contenedorTres">
    <div class="row">
      <span><br></br></span>
      <div class = "col-lg-4">
        Aqui va el mapa
      </div>

      <div class = "col-lg-4">
        <div class="list-group">
          <a v-for="(item, index) in items" :key="index" v-bind:href="'TareasId/'+item.id" class="list-group-item list-group-item-action" aria-current="true" style="width: 380px; background-color: #169691;">
            <div class="d-flex w-100 justify-content-between">
              <h5 class="mb-1">{{item.nombre}}</h5>
              <small>{{item.fecha}}</small>
            </div>
            <p class="mb-1">{{item.descripcion}}</p>
          </a>
        </div>
      </div>
    </div>

    <div>
        <a href="/NuevaTarea">
          <button type="button" style="position: absolute; top: 60px; left: 650%; width : 150px ; padding: 10px" class="btn btn-success">Nueva Tarea</button>
        </a>
    </div>
    </div>
    
</body>
</template>

<style>
.scrollDiv {
    height: 100vh;
    max-height: 100vh;
}
</style>

<script>
export default {
    //Función que contiene los datos del componente
    data(){
        return{
            //Lista de ítems a mostrar
            items:[],
            param:[]
        }
    },
    methods:{
        //Función asíncrona para consultar los datos
        getData: async function(){
            try {
                let response2 = await this.$axios.get("/tarea");
                this.items = response2.data;
                console.log(response2);
            } catch (error) {
                console.log('error', error);
            }
        }
    },
    //Función que se ejecuta al cargar el componente
    created:function(){
        this.getData();
    }
}
</script>
