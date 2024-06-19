<template>
  <TourenListeZeile v-for="t in touren" v-bind:key="t.id" v-bind:tour="t"></TourenListeZeile>
</template>

<script setup lang="ts">
import TourenListeZeile from './TourenListeZeile.vue'
import type { ITourDTD } from '@/views/TourenListeView.vue'
const props = defineProps<{ touren: ITourDTD[] }>()
</script>

<style scoped></style>
