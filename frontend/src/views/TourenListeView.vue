<template>
  <div class="header">
    <h1>Das aktuelle Mitfahrangebot</h1>
    <input type="text" v-model="search" />
    <button v-on:click="resetSearch">Zurücksetzen</button>
  </div>
  <TourenListe :touren="tourenGefiltert"></TourenListe>
</template>

<script setup lang="ts">
/*
 * TourenListe-Komponente bitte in src/components/tour selbst implementieren
 */
import TourenListe from '@/components/tour/TourenListe.vue'
import { computed, ref } from 'vue'
import { useTourenStore } from '@/stores/tourenstore'

const { tourdata, updateTourListe } = useTourenStore()

updateTourListe()

const search = ref('')

const tourenGefiltert = computed(() => {
  return tourdata.state.tourliste.filter(
    (e) =>
      e.startOrtName.toLowerCase().includes(search.value.toLowerCase()) ||
      e.zielOrtName.toLowerCase().includes(search.value.toLowerCase())
  )
})

function resetSearch() {
  search.value = ''
}

export interface ITourDTD {
  id: number
  abfahrDateTime: string
  preis: number
  plaetze: number
  buchungen: number
  startOrtName: string
  startOrtId: number
  zielOrtName: string
  zielOrtId: number
  anbieterName: string
  anbieterId: number
  distanz: number
  info: string
}
</script>

<style scoped>
.header {
  margin: 5% 0 0 5%;
}
</style>
