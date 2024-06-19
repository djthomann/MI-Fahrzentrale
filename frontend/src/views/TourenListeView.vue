<template>
  <h1 class="title">Das aktuelle Mitfahrangebot</h1>

  <TourenListe :touren="tourliste"></TourenListe>
</template>

<script setup lang="ts">
/*
 * TourenListe-Komponente bitte in src/components/tour selbst implementieren
 */
import TourenListe from '@/components/tour/TourenListe.vue'
import { ref } from 'vue'

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

/*
 * Provisorische Beispieldaten in "tourliste"-Ref bereitstellen,
 * kommt künftig über einen REST-Endpunkt von unserem Backend
 */
const tourliste = ref<ITourDTD[]>(
  JSON.parse(`
[
  {"id":102,"abfahrDateTime":"1010-11-22T10:01:00","preis":10,"plaetze":1,"buchungen":0,"startOrtName":"Wiesbaden","startOrtId":1,"zielOrtName":"Schöngleina","zielOrtId":2302,"anbieterName":"Joghurta Biffel","anbieterId":1,"distanz":263.90476912561047,"info":""},
  {"id":1,"abfahrDateTime":"2023-11-17T19:30:00","preis":5,"plaetze":3,"buchungen":0,"startOrtName":"Wiesbaden","startOrtId":1,"zielOrtName":"Taucha","zielOrtId":3,"anbieterName":"Senkel","anbieterId":1552,"distanz":333.2123954484251,"info":"Auf nach Taucha!"},
  {"id":252,"abfahrDateTime":"2024-05-10T16:27:00","preis":12,"plaetze":2,"buchungen":0,"startOrtName":"Wiesbaden","startOrtId":1,"zielOrtName":"Bochum","zielOrtId":603,"anbieterName":"Glogomir Elektro","anbieterId":1652,"distanz":173.29384069882823,"info":""},
  {"id":2,"abfahrDateTime":"2033-02-01T18:00:00","preis":17,"plaetze":3,"buchungen":0,"startOrtName":"Wiesbaden","startOrtId":1,"zielOrtName":"Clenze","zielOrtId":4,"anbieterName":"Joghurta Biffel","anbieterId":1,"distanz":369.6370869573237,"info":"Alle wollen nach Clenze - Du doch sicherlich auch! Oder nicht? Doch!"}
]
`)
)
</script>

<style scoped></style>
