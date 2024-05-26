# Veteriner Yönetim Sistemi

Veteriner Yönetim Sistemi, veteriner kliniklerinin günlük işlerini düzenlemeyi ve yönetmeyi amaçlayan bir REST API'dir. Bu API, veteriner çalışanlarının veteriner hekimleri, müşterileri, hayvanları ve aşıları, ayrıca randevuları yönetmelerine olanak tanır.

## Kullanılan Teknolojiler

- Java
- Spring Boot
- Maven
- PostgreSql
- REST
- Postman
- IntelliJ
- Git
- GitHub

## Özellikler

- **Veteriner Hekim Yönetimi**: Veteriner hekimleri ekleme, güncelleme, görüntüleme ve silme yetenekleri.
- **Müşteri Yönetimi**: Müşterileri kaydetme, bilgilerini güncelleme, listeleme ve silme yetenekleri.
- **Hayvan Yönetimi**: Hayvanları sisteme kaydetme, bilgilerini güncelleme, listeleme ve silme yetenekleri.
- **Aşı Yönetimi**: Hayvanlara uygulanan aşıları kaydetme, bilgilerini güncelleme, listeleme ve silme yetenekleri.
- **Randevu Yönetimi**: Veteriner hekimler için randevular oluşturma, güncelleme, görüntüleme ve silme yetenekleri.
- **Çeşitli Filtreleme Yetenekleri**

## Ekran Görüntüleri ve UML Diyagramları

![UML diagrams](https://github.com/Snmzgrkn/veterinary/assets/56911478/54fc553f-9bf9-4518-915e-db02bc6ce826)


## Veri Tabanı Tabloları
- **vaccines**
![vaccines](https://github.com/Snmzgrkn/veterinary/assets/56911478/41a7f980-60a6-4f95-90cf-dd416c52d5ed)
<br>
- **doctors**
![doctors](https://github.com/Snmzgrkn/veterinary/assets/56911478/7920df63-62ec-4119-8c26-8d3c4619d197)
<br>

- **customers**
![customers](https://github.com/Snmzgrkn/veterinary/assets/56911478/ea0bc581-23cb-469b-9c97-012df114e48d)
<br>

- **available_dates**
![available_dates](https://github.com/Snmzgrkn/veterinary/assets/56911478/c4de3d49-6984-4507-b4b5-7e0cd1124d5e)
<br>

- **appointments**
![appointments](https://github.com/Snmzgrkn/veterinary/assets/56911478/7534890a-d621-42db-865e-d01b84e0af63)
<br>

-**animals**
![animals](https://github.com/Snmzgrkn/veterinary/assets/56911478/ab668ef2-2405-4846-870f-42c614e63112)


## API Kullanımı

Aşağıda, API'nin sunduğu temel endpoint'lerin bir listesi bulunmaktadır:

### Müşteriler

- `/api/v1/customers/{id}` (GET): Belirtilen ID'ye sahip müşteriyi getirir
- `/api/v1/customers/{id}` (PUT): Belirtilen ID'ye sahip müşteriyi günceller
- `/api/v1/customers/{id}` (DELETE): Belirtilen ID'ye sahip müşteriyi siler
- `/api/v1/customers` (GET): Tüm müşterileri getirir
- `/api/v1/customers` (POST): Müşteri ekler
- `/api/v1/customers/searchByName` (GET): İsme göre müşterileri arar

### Hayvanlar

- `/api/v1/animals/{id}` (GET): Belirtilen ID'ye sahip hayvanı getirir
- `/api/v1/animals/{id}` (PUT): Belirtilen ID'ye sahip hayvanı günceller
- `/api/v1/animals/{id}` (DELETE): Belirtilen ID'ye sahip hayvanı siler
- `/api/v1/animals` (GET): Tüm hayvanları getirir
- `/api/v1/animals` (POST): Hayvan ekler
- `/api/v1/animals/searchByName` (GET): İsme göre hayvanları filtreler
- `/api/v1/animals/searchByCustomer` (GET): Sahiplerine göre hayvanları filtreler

### Aşılar

- `/api/v1/vaccines/{id}` (GET): Belirtilen ID'ye sahip aşıyı getirir
- `/api/v1/vaccines/{id}` (PUT): Belirtilen ID'ye sahip aşıyı günceller
- `/api/v1/vaccines/{id}` (DELETE): Belirtilen ID'ye sahip aşıyı siler
- `/api/v1/vaccines` (GET): Tüm aşıları getirir
- `/api/v1/vaccines` (POST): Aşı ekler
- `/api/v1/vaccines/searchByVaccinationRange` (GET): Belirli tarih aralığına göre aşı kayıtlarını getirir
- `/api/v1/vaccines/searchByAnimal` (GET): Belirli bir hayvana ait tüm aşı kayıtlarını getirir

### Veteriner Hekimler

- `/api/v1/doctors/{id}` (GET): Belirtilen ID'ye sahip hekimi getirir
- `/api/v1/doctors/{id}` (PUT): Belirtilen ID'ye sahip hekimi günceller
- `/api/v1/doctors/{id}` (DELETE): Belirtilen ID'ye sahip hekimi siler
- `/api/v1/doctors` (GET): Tüm hekimleri getirir
- `/api/v1/doctors` (POST): Hekim ekler

### Müsait Günler

- `/api/v1/available_dates/{id}` (GET): Belirtilen ID'ye sahip müsait günü getirir
- `/api/v1/available_dates/{id}` (PUT): Belirtilen ID'ye sahip müsait günü günceller
- `/api/v1/available_dates/{id}` (DELETE): Belirtilen ID'ye sahip müsait günü siler
- `/api/v1/available_dates` (GET): Tüm müsait günleri getirir
- `/api/v1/available_dates` (POST): Müsait gün ekler

### Randevular

- `/api/v1/appointments/{id}` (GET): Belirtilen ID'ye sahip randevuyu getirir
- `/api/v1/appointments/{id}` (PUT): Belirtilen ID'ye sahip randevuyu günceller
- `/api/v1/appointments/{id}` (DELETE): Belirtilen ID'ye sahip randevuyu siler
- `/api/v1/appointments` (GET): Tüm randevuları getirir
- `/api/v1/appointments` (POST): Randevu ekler
- `/api/v1/appointments/searchByDoctorAndDateRange` (GET): Belirli tarih aralığına ve hekime göre randevuları filtreler
- `/api/v1/appointments/searchByAnimalAndDateRange` (GET): Belirli tarih aralığına ve hayvana göre randevuları filtreler

## Kurulum

1. Projeyi klonlayın:
   ```sh
   git clone https://github.com/Snmzgrkn/veterinary.git
