import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'path'

export default defineConfig({
  plugins: [react()],
  server: {
    host: '0.0.0.0',  // 모든 네트워크 인터페이스에서 접근 가능하도록 설정
    proxy: {
      '/api': {
        target: process.env.VITE_API_URL || "http://localhost:8080",
        changeOrigin: true,
      },
    },
  },
  resolve: {
    alias: {
      '@': path.resolve(__dirname, "./src"),
    },
  },
  build: {
    outDir: 'dist',  // 빌드 출력 디렉토리 설정
    emptyOutDir: true,  // 빌드 전 출력 디렉토리 비우기
  }
})