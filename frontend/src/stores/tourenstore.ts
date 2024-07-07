import { reactive, readonly, computed, ref } from 'vue'
import { Client, type Message } from '@stomp/stompjs'
import { defineStore } from 'pinia'
import type { ITourDTD } from './ITourDTD'
import { useInfo } from '@/composables/useInfo'
import type { IFrontendNachrichtEvent } from '@/service/IFrontendNachrichtEvent'

const { info, loescheInfo, setzeInfo } = useInfo()

export const useTourenStore = defineStore('tourenstore', () => {
  const state = reactive({ tourliste: Array<ITourDTD>(), ok: false })

  const wsurl = `ws://${window.location.host}/stompbroker`
  const DEST = '/topic'

  let stompClient: Client

  function startTourLiveUpdate() {
    if (stompClient == null) {
      stompClient = new Client({ brokerURL: wsurl })

      stompClient.onWebSocketError = (event) => {
        setzeInfo(`WebSocket Error: ${event.message}`)
      }
      stompClient.onStompError = (frame) => {
        setzeInfo(`STOMP Error: ${JSON.stringify(frame)}`)
      }

      stompClient.onConnect = (frame) => {
        setzeInfo('Erfolgreicher Verbindungsaufbau zum Server')
        stompClient.subscribe(DEST, (message) => {
          console.log('Subscribed')
          const event: IFrontendNachrichtEvent = JSON.parse(message.body)
          console.log(event)

          if (event.messageType == 'TOUR') {
            console.log('Update der Touren Liste')
            updateTourListe()
          }
        })
      }
      stompClient.onDisconnect = () => {
        /* Verbindung abgebaut*/
      }

      stompClient.activate()
    } else {
      console.log('Es gibt schon einen Stomp Client')
    }
  }

  async function updateTourListe() {
    try {
      const resp = await fetch('/api/tour', {
        method: 'GET'
      })
      if (!resp.ok) {
        throw new Error(resp.statusText)
      }
      const jsondata = await resp.json()
      console.log(jsondata)
      state.tourliste = jsondata
      state.ok = true
      startTourLiveUpdate()
    } catch (reason) {
      setzeInfo(`Fehler: ${reason}`)
      state.ok = false
    }
  }

  return {
    tourdata: state,
    updateTourListe
  }
})
