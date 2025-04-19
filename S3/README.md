# 🌐 Hosting a Static Website on Amazon S3

This project demonstrates how to host a static website using **Amazon S3**. Follow these steps to create a publicly accessible site with HTML files hosted in an S3 bucket.

---

## 📋 Topics Covered

1. [Create an S3 Bucket](#step-1-create-an-s3-bucket)  
2. [Enable Static Website Hosting](#step-2-enable-static-website-hosting)  
3. [Edit Block Public Access Settings](#step-3-edit-block-public-access-settings)  
4. [Add a Public Bucket Policy](#step-4-add-a-bucket-policy-that-makes-your-bucket-content-publicly-available)  
5. [Configure an Index Document](#step-5-configure-an-index-document)  
6. [Configure an Error Document](#step-6-configure-an-error-document)  
7. [Test Your Website Endpoint](#step-7-test-your-website-endpoint)  
8. [Clean Up Resources](#step-8-clean-up)  

---

## ✅ Step 1: Create an S3 Bucket

- Go to the [Amazon S3 Console](https://console.aws.amazon.com/s3/).
- Click **Create bucket**.
- Enter a unique bucket name (e.g., `my-awesome-site`).
- Choose a region close to your users.
- Leave the default settings and **uncheck "Block all public access"** (you'll configure this later).
- Click **Create**.

---

## ✅ Step 2: Enable Static Website Hosting

- Open your bucket.
- Go to the **Properties** tab.
- Scroll to **Static website hosting** → **Edit**.
- Select **Use this bucket to host a website**.
- Provide your **index document** (e.g., `index.html`) and optionally an **error document** (e.g., `404.html`).
- Click **Save changes**.
- Note the **Website endpoint** — this is your public site URL.

---

## ✅ Step 3: Edit Block Public Access Settings

- Open the bucket → **Permissions** tab.
- Under **Block public access (bucket settings)** → **Edit**.
- Uncheck **Block all public access**.
- Click **Save changes**.

> ⚠️ This makes your content publicly accessible. Be sure you understand the risks.

---

## ✅ Step 4: Add a Bucket Policy That Makes Your Bucket Content Publicly Available

- Go to **Permissions** → **Bucket Policy** → **Edit**.
- Add the following JSON policy:

\```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "PublicReadGetObject",
      "Effect": "Allow",
      "Principal": "*",
      "Action": "s3:GetObject",
      "Resource": "arn:aws:s3:::your-bucket-name/*"
    }
  ]
}
\```

> Replace `your-bucket-name` with your actual S3 bucket name.

- Click **Save changes**.

---

## ✅ Step 5: Configure an Index Document

1. Create an `index.html` file. Example:

\```html
<!DOCTYPE html>
<html>
<head>
  <title>Welcome</title>
</head>
<body>
  <h1>Welcome to my website</h1>
  <p>Now hosted on Amazon S3!</p>
</body>
</html>
\```

2. Upload it to your bucket via **Upload** or drag and drop.
3. Ensure the file name matches exactly the one set in the static website config (case-sensitive).

---

## ✅ Step 6: Configure an Error Document

1. Create a `404.html` file. Example:

\```html
<!DOCTYPE html>
<html>
<head>
  <title>Page Not Found</title>
</head>
<body>
  <h1>404 - Page Not Found</h1>
  <p>Oops! The page you are looking for does not exist.</p>
</body>
</html>
\```

2. Upload it to your bucket.
3. Make sure the file name exactly matches what you entered in the hosting settings.

---

## ✅ Step 7: Test Your Website Endpoint

- Go to the **Properties** tab.
- Scroll down to **Static website hosting**.
- Click the **Endpoint URL** — it will open your live website.

> ℹ️ Amazon S3 only supports HTTP. To use HTTPS, consider using [Amazon CloudFront](https://aws.amazon.com/cloudfront/).

---

## ✅ Step 8: Clean Up

If this setup was for practice:

- Delete the S3 bucket and any associated resources to stop incurring charges.
- Visit the AWS console to manage your resources.

---

## 📎 Resources

- [AWS S3 Docs](https://docs.aws.amazon.com/s3/)
- [Static Website Hosting on S3](https://docs.aws.amazon.com/AmazonS3/latest/userguide/WebsiteHosting.html)
- [Use CloudFront with S3](https://docs.aws.amazon.com/AmazonCloudFront/latest/DeveloperGuide/DownloadDistS3AndCustomOrigin.html)

---
