<template>
  <span>Das ist eine Tour</span>
</template>

<script setup lang="ts">
import ITourDTD from '@/components/tour/TourenListeView.vue'
const props = defineProps({ tour: ITourDTD })
</script>

<style scoped></style>
