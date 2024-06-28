<template>
  <table class="table">
    <thead>
      <tr>
        <th class="cell-small">ID</th>
        <th>Anbieter</th>
        <th>Abfahrt</th>
        <th>Start</th>
        <th>Ziel</th>
        <th>Preis</th>
        <th>Plätze</th>
        <th>Frei</th>
        <th class="cell-small"></th>
      </tr>
    </thead>
    <tbody>
      <TourenListeZeile v-for="t in touren" v-bind:key="t.id" v-bind:tour="t"></TourenListeZeile>
    </tbody>
  </table>
</template>

<script setup lang="ts">
import TourenListeZeile from './TourenListeZeile.vue'
import type { ITourDTD } from '@/stores/ITourDTD'
defineProps<{ touren: ITourDTD[] }>()
</script>

<style scoped></style>
