
function loadAndDisplayUsers() {
 const connectedUser = localStorage.getItem("connectedUser");
 if (!connectedUser) {
     window.location = 'login.html';
     return;
 }

 const userListElement = document.getElementById('userList');
 userListElement.innerHTML = 'Loading...';
 fetch('https://localhost:8080/api//v1/users' )
    .then((res) =>{
     return res.json();
    })
    .then((data)=> {
        console.log(data);
        displayUser(data,userListElement)

    });
 function displayUser(userList,userListElement){
     userListElement.innerHTML = "";
     userList.forEach(user => {
         const listItem = document.createElement('li');
         listItem.innerHTML = `
         <div>
         <i class = "fa fa-user-circle"></i>
         ${user.name}<i class="user-email">(${user.email})</i>
         <div/>
         <i class = "fa fa-lightbulb-o ${user.status=== "online" ? "online" : "offline"}"></i>
         `;
         userListElement.appendChild(listItem);
     })
 }
 window.addEventListener("load", displayUser);
 function handleLogout(){
     fetch('https://localhost:8080/api/v1/users/logout', {
         method: 'POST',
         headers: {
             'Content-Type': 'application/json'
         },
         body: localStorage.getItem('connectedUser')
     })
             .then((response) => {
                 return response;
             })
         .then((data)=> {
             localStorage.removeItem('connectedUser');
             localStorage.removeItem('connectedUser');
             window.location = 'login.html';
         })
     }
 const logoutButton = document.getElementById('logoutBtn');
 logoutButton.addEventListener('click', handleLogout);

 function handleNewMeeting(){
     const connectedUser = JSON.parse(localStorage.getItem('connectedUser'));
     window.open(`videocall.html?username=${connectedUser.username}`,"_blank");

 }
 const newMeetingButton = document.getElementById('newMeetingBtn');
 newMeetingButton.addEventListener('click', handleNewMeeting);


 function handleJoinMeeting(){
     const roomId = document.getElementById('meetingName').value;

   const connectedUse =  JSON.parse(localStorage.getItem('connectedUser'));
     const url = `videocall.html?roomID=${roomId}&username=${connectedUser.username}`;
     window.open(url, '_blank');
 }

 const joinMeetingButton = document.getElementById('joinMeetingBtn');
 joinMeetingButton.addEventListener('click', handleJoinMeeting);

}
