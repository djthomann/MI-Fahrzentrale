<template>
  <div class="header">
    <h1>Tour {{ tourid }}: {{ tour?.startOrtName }} nach {{ tour?.zielOrtName }}</h1>
    <span>{{ tour?.info }}</span>
  </div>
  <ul>
    <li>Abfahrt am {{ tour?.abfahrDateTime }}</li>
    <li>Preis {{ tour?.preis }} für {{ tour?.distanz }}</li>
    <li>Anbieter ist {{ tour?.anbieterName }}</li>
    <li>{{ tour?.plaetze }} Plätze gibt es insgesamt, frei sind {{ freiePlaetze }}</li>
  </ul>
</template>

<script setup lang="ts">
import { useTourenStore } from '@/stores/tourenstore'
import { useInfo } from '@/composables/useInfo'
import { computed } from 'vue'
const { tourdata, updateTourListe } = useTourenStore()
updateTourListe()
const { info, loescheInfo, setzeInfo } = useInfo()
loescheInfo()

const props = defineProps<{ tourid: string }>()

const tour = tourdata.state.tourliste.find((value) => value.id === parseInt(props.tourid))

var freiePlaetze: any

if (tour) {
  freiePlaetze = computed(() => tour.plaetze - tour.buchungen)
  if (tour.distanz > 300) {
    setzeInfo(
      `Achtung! Die Tour von ${tour.startOrtName} nach ${tour.zielOrtName} ist länger als 300km`
    )
  }
} else {
  setzeInfo('Tour konnte nicht gefunden werden')
}
</script>

<style scoped></style>
