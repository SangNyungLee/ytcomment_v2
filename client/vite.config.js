import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server:{
    proxy: {
      '/api':{
        target : "http://localhost:8080",
        changeOrigin: true,
        /**
         * 정규식 표현을 써서 localhost:8000/api에서 
         * api만 지우고 싶으면 위에 정규식을 사용해서 지워버리면 됨
        */
        // rewrite: (path) => path.replace(/^\/api/, ''),
      },
    },
  },
})
