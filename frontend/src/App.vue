<script setup lang="ts">
import { useInfo } from './composables/useInfo'
import router from './router'
import TourView from './views/TourView.vue'

var info = useInfo().info
function loescheInfo() {
  useInfo().loescheInfo()
}
function setzeInfo(msg: string) {
  useInfo().setzeInfo(msg)
}
setzeInfo('Meine Nachricht')
</script>

<template>
  <header>
    <div>
      <img th:src="@{/icons/Logo-Hochschule-RheinMain.png}" />
      <a th:text="#{kopf.benutzer}">Benutzer bearbeiten</a>
      <a>Orte</a>
      <RouterLink :to="{ name: 'TourenListeView' }">Touren</RouterLink>
    </div>

    <nav>
      <a>DE</a>
      <a>HE</a>
      <a>BY</a>
      <a>NL</a>
      <a>EN</a>
    </nav>
  </header>

  <span v-show="info !== ''" class="info-box"
    >{{ info }}<button class="info-button" v-on:click="loescheInfo" type="button">X</button></span
  >

  <RouterView></RouterView>

  <footer>
    <a th:href="@{~/kontakt}" th:text="#{fuss.kontakt}">Kontakt</a>
    <a th:href="@{~/impressum}" th:text="#{fuss.impressum}">Impressum</a>
  </footer>
</template>

<style scoped></style>
