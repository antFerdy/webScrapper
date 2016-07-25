// Copyright (c) 2009 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.



var port = chrome.runtime.connect();
document.body.onmouseover = document.body.onmouseout = document.body.onclick = handler;

function handler(event) {
  if (event.type == 'mouseover') {
    event.target.style.background = 'pink'
  }

  if (event.type == 'mouseout') {
    event.target.style.background = ''
  }

  if(event.type == 'click') {
  	// console.log(getPathTo(event.target));
  	// document.body.onmouseover = document.body.onmouseout = document.body.onclick = '';
    var target= 'target' in event? event.target : event.srcElement; // another IE hack

    var root= document.compatMode==='CSS1Compat'? document.documentElement : document.body;
    var mxy= [event.clientX+root.scrollLeft, event.clientY+root.scrollTop];

    var path= getPathTo(target);
    var txy= getPageXY(target);
    var message = 'You clicked the element '+path+' at offset '+(mxy[0]-txy[0])+', '+(mxy[1]-txy[1]);
    console.log(message);

    var objectInfo = {
      "element":event.target.innerHTML,
      "path":path
    };

    port.postMessage(objectInfo);
    document.body.onmouseover = document.body.onmouseout = document.body.onclick = null;
    
  }
}

// chrome.runtime.connect().postMessage(additionalInfo);


function getPathTo(element) {
    if (element.id!=='')
        return "//*[@id='"+element.id+"']";
    
    if (element===document.body)
        return element.tagName.toLowerCase();

    var ix= 0;
    var siblings= element.parentNode.childNodes;
    for (var i= 0; i<siblings.length; i++) {
        var sibling= siblings[i];
        
        if (sibling===element) return getPathTo(element.parentNode) + '/' + element.tagName.toLowerCase() + '[' + (ix + 1) + ']';
        
        if (sibling.nodeType===1 && sibling.tagName === element.tagName) {
            ix++;
        }
    }
}

function getPageXY(element) {
    var x= 0, y= 0;
    while (element) {
        x+= element.offsetLeft;
        y+= element.offsetTop;
        element= element.offsetParent;
    }
    return [x, y];
}