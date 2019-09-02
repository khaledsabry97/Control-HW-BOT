### TANMO_android
Control home device through mobile app using WiFi connection.

## Target:
- design an electronic device that can turn on and off home device using wifi
  connection through mobile app

## description:
- we will design a mobile app that control home device through wifi connection.
- the app will turn device on and off though direct control.
- the app could make device run for a period of time then turn it off.
- the app could turn device on for a period of time for any days selected in the week
  so, the device will run automatically for period of time in the specified days. 



## block diagram:

       _____________              _________________            ________
       | mobile app|-----wifi---->|hardware device|---wires--->|device|
       ^^^^^^^^^^^^^              ^^^^^^^^^^^^^^^^^            ^^^^^^^^

- hardware device will be connected in parallel with device switch

## tasks list:
# action tab:
- [x] single activity on/off button green/red
- [x] add status indicator to single activity could be the button itself
- [x] add toast error messages to single activity could be ignored its 
      good to know your http request succeeded or not
- [x] put on and off buttons block in a single fragment
- [x] make new fragment for timer block
- [x] add timer picker
- [x] add count down timer code
- [x] make button start a timer and send on 
- [x] after timer finished button reset and send off
# configuration tab:
- [x] create new fragment for login block
- [x] create json stucture to be sent in every request
- [x] add device Id to every reqeust to be as token for device and app
- [x] create Device password text field
- [x] create save button
- [x] relate login info to save button
 communication 
# schedule tab:
- [x] create new fragment for each block if code is big and if not make all
      of them in one activity
- [x] create save button to send request on specified conditions
- [x] add clock picker
- [x] relate clock picker output to save button code
- [x] add days selection boxes
- [x] relate days output to save button code
- [x] add timer picker
- [x] add count down timer code
- [x] relate timer to save button code
- [x] rearrange schedule tab code in most usable way

# about tab:
- [x] text field include company name
- [x] text field include website
- [x] text field include contact email

## contribution guide:
- refer to "docs" folder for documentation.
- update android studio to last version.
- we work on project in the procedural sequence that mentioned in tasks list
  above, if prefer other make sure you do not affect other project
  components.
- as always it is preferred test new feature in a new branch test or new
  test project before trying add it to main branch.
- feel free to choose the task you want to work on as long as it does not
  affect other project components.
