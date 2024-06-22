import { reactive, readonly, ref } from 'vue'

const state = ref('')

export function useInfo() {
  const info = readonly(state)

  function loescheInfo() {
    state.value = ''
  }

  function setzeInfo(msg: string) {
    state.value = msg
  }

  return {
    info,
    loescheInfo,
    setzeInfo
  }
}
