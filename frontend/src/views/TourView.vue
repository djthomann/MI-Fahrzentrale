<template>
  <h1>Das ist mein Test</h1>
  <h1>{{ tourid }}</h1>
  <h1>{{ tour }}</h1>
</template>

<script setup lang="ts">
import { useTourenStore } from '@/stores/tourenstore'
const { tourdata, updateTourListe } = useTourenStore()
updateTourListe()

const props = defineProps<{ tourid: string }>()

const tour = tourdata.state.tourliste[parseInt(props.tourid)]
</script>

<style scoped></style>
