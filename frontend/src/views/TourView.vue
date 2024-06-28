<template>
  <h1>{{ tour }}</h1>
</template>

<script setup lang="ts">
import { useTourenStore } from '@/stores/tourenstore'
import { useInfo } from '@/composables/useInfo'
const { tourdata, updateTourListe } = useTourenStore()
updateTourListe()
const { info, loescheInfo, setzeInfo } = useInfo()
loescheInfo()

const props = defineProps<{ tourid: string }>()

const tour = tourdata.state.tourliste.find((value) => value.id === parseInt(props.tourid))

if (tour) {
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
