import { reactive, readonly, computed, ref } from 'vue'
import { defineStore } from 'pinia'
import type { ITourDTD } from './ITourDTD'
import { useInfo } from '@/composables/useInfo'

const { info, loescheInfo, setzeInfo } = useInfo()

export const useTourenStore = defineStore('tourenstore', () => {
  const state = reactive({ tourliste: Array<ITourDTD>(), ok: false })

  async function updateTourListe() {
    try {
      const resp = await fetch('/api/tour', {
        method: 'GET'
      })
      if (!resp.ok) {
        throw new Error(resp.statusText)
      }
      const jsondata = await resp.json()
      state.tourliste = jsondata
      state.ok = true
    } catch (reason) {
      setzeInfo(`Fehler: ${reason}`)
      state.ok = false
    }
  }

  return {
    tourdata: reactive({ state }),
    updateTourListe
  }
})
