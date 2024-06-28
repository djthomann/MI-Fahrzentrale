<template>
  <tr
    th:classappend="${status.odd}? odd-li : even-li"
    th:each="ele,status : ${touren}"
    class="even-li"
  >
    <td>{{ tour.id }}</td>
    <td>{{ tour.anbieterName }}</td>
    <td>{{ tour.abfahrDateTime }}</td>
    <td>{{ tour.startOrtName }}</td>
    <td>{{ tour.zielOrtName }}</td>
    <td>{{ tour.preis }}</td>
    <td>{{ tour.plaetze }}</td>
    <td>{{ freiePlaetze }}</td>
    <td>
      <RouterLink :to="{ name: 'TourView', params: { tourid: tour.id } }">Details</RouterLink>
    </td>
  </tr>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import type { ITourDTD } from '@/views/TourenListeView.vue'

const props = defineProps<{ tour: ITourDTD }>()

var freiePlaetze = computed(() => props.tour.plaetze - props.tour.buchungen)
</script>

<style></style>
