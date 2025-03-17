import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'  // 기존 플러그인 유지

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react(), tailwindcss()],
  server: {
    host: '0.0.0.0',  // 모든 네트워크 인터페이스에서 접근 가능하도록 설정
    proxy: {
      '/api': {
        target: process.env.VITE_API_URL || "http://localhost:8080",
        changeOrigin: true,
      },
    },
  },
  build: {
    outDir: 'dist',  // 빌드 출력 디렉토리 설정
    emptyOutDir: true,  // 빌드 전 출력 디렉토리 비우기
  }
})