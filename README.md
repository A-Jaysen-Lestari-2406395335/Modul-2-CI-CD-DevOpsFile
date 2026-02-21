### Reflection 
> List the code quality issue(s) that you fixed during the exercise and explain your strategy
on fixing them.
  
* Masalah Dependency Verification pada Gradle
Proses build di dalam Docker gagal karena file verification-metadata.xml menyebabkan error verifikasi dependensi.
Karena fitur dependency verification tidak menjadi fokus pada modul ini dan justru menghambat proses CI/CD, saya menghapus file tersebut agar proses build berjalan stabil.


* Masalah Permission pada Gradle di Docker
Docker build gagal dengan pesan Permission denied saat menjalankan ./gradlew.
Saya memperbaikinya dengan menambahkan perintah RUN chmod +x gradlew di dalam Dockerfile agar Gradle Wrapper dapat dieksekusi di dalam container.

Strategi utama saya dalam memperbaiki masalah-masalah tersebut adalah:
- Membaca log error dari CI/CD secara detail.
- Mengidentifikasi akar permasalahan dari setiap error.
- Melakukan perbaikan kecil dan terfokus, lalu menguji ulang melalui workflow CI.
---
> 2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current
     implementation has met the definition of Continuous Integration and Continuous
     Deployment? Explain the reasons (minimum 3 sentences)!

Menurut saya, implementasi workflow yang dibuat sudah memenuhi konsep Continuous Integration (CI) karena setiap push ke repository secara otomatis menjalankan proses build, unit test, dan analisis kualitas kode sehingga kesalahan dapat terdeteksi lebih awal. Implementasi ini juga memenuhi konsep Continuous Deployment (CD) karena setiap perubahan pada branch utama langsung dideploy ke platform PaaS tanpa intervensi manual. Dengan pipeline ini, proses integrasi, pengujian, dan deployment menjadi otomatis, konsisten, dan mengurangi risiko kesalahan akibat proses manual.