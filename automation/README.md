# UiPath Automation

This directory contains the UiPath bot for the Event Registration System.

## Bots

### Option A: Attendee Report Bot
Exports a list of attendees for a given event from the web app into an Excel report.

### Option B: Bulk Registration Bot
Reads a list of attendees from an Excel file and registers them for a specified event through the web UI.

## How to Run

1. Open UiPath Studio.
2. Open the project from the `AttendeeReportBot/` (or `BulkRegistrationBot/`) directory.
3. Ensure the web application is running at `http://localhost:8080`.
4. Run `Main.xaml` from UiPath Studio.

## Prerequisites
- UiPath Studio (Community Edition or higher)
- Chrome or Edge browser with UiPath browser extension installed
- The Spring Boot web app running locally

## Sample Data
See the `sample-data/` folder for example input/output files.
