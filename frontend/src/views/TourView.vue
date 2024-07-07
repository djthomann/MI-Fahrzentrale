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
import { computed, ref, onMounted } from 'vue'
import type { ITourDTD } from './TourenListeView.vue'

const { tourdata, updateTourListe } = useTourenStore()
const { info, loescheInfo, setzeInfo } = useInfo()
loescheInfo()

const props = defineProps<{ tourid: string }>()

const tour = ref<ITourDTD>()

const loadTour = async () => {
  if (!tourdata.ok) {
    await updateTourListe()
  }

  tour.value = tourdata.tourliste.find((value) => value.id === parseInt(props.tourid))

  if (tour.value) {
    if (tour.value.distanz > 300) {
      setzeInfo(
        `Achtung! Die Tour von ${tour.value.startOrtName} nach ${tour.value.zielOrtName} ist länger als 300km`
      )
    }
  } else {
    setzeInfo('Tour konnte nicht gefunden werden')
  }
}

onMounted(loadTour)

const freiePlaetze = computed(() => {
  return tour.value ? tour.value.plaetze - tour.value.buchungen : 0
})
</script>

<style scoped></style>
