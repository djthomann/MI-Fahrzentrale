<template>
  <span v-for="ele in tour" v-bind:key="ele">{{ ele }}</span
  ><br />
</template>

<script setup lang="ts">
import type { ITourDTD } from '@/views/TourenListeView.vue'

const props = defineProps<{ tour: ITourDTD }>()
</script>

<style></style>
