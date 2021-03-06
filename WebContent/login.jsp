<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Whatsapp Web | Login</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<link
			rel="stylesheet"
			href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
			integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
			crossorigin="anonymous"
		/>
    <link rel="stylesheet" href="css/login.css">
</head>
<body class="h-80 py-5">
    <main class="d-flex align-items-stretch h-100 w-75 mx-auto">
        <section class="side bg-white border-right">
            <header class="d-flex">
                <div class="profile-photo rounded-circle my-auto ml-2 mr-auto"></div>
                <a href="#" title="Status">
                    <i class="fas fa-circle-notch text-secondary"></i>
                </a>
                <a href="#" title="New Chat">
                    <i class="fas fa-comment-dots text-secondary"></i>
                </a>
                <a href="#" title="Menu">
                    <i class="fas fa-ellipsis-v text-secondary"></i>
                </a>
            </header>
            <div class="search-bar w-100 py-2 px-3">
                <div class="input-group">
                    <div class="input-group-prepend position-absolute search-button-wrapper">
                        <button class="btn border-0 text-black-50 ml-2" type="button">
                            <i class="fas fa-search"></i>
                        </button>
                    </div>

                    <input type="text" class="form-control rounded-pill pl-5" placeholder="Search or start a new chat">
                </div>
            </div>
            <section class="contacts">
                <ul class="list-unstyled">
                    <li class="media">
                        <div class="profile-photo rounded-circle mb-2 mt-3 ml-2 mr-3"></div>
                        <div class="media-body">
                            <h6 class="mt-3 mb-1 text-secondary">My Friend</h6>
                            <p class="last-message text-black-50 border-bottom pb-3 mb-1">
                                <i class="fas fa-check-double read"></i>
                                My friend's last message
                            </p>
                        </div>
                    </li>
                    <li class="media">
                        <div class="profile-photo rounded-circle mb-2 mt-3 ml-2 mr-3"></div>
                        <div class="media-body">
                            <h6 class="mt-3 mb-1 text-secondary">My Other Friend</h6>
                            <p class="last-message text-black-50 border-bottom pb-3 mb-1">
                                <i class="fas fa-check"></i>
                                My Other friend's unread message
                            </p>
                        </div>
                    </li>
                </ul>
            </section>
        </section>
        <section class="chat flex-fill bg-light d-flex flex-column">
            <header class="d-flex">
                <div class="profile-photo rounded-circle my-auto mx-2"></div>
                <div class="mr-auto">
                    <p class="mt-2 mb-0 text-secondary font-weight-bold">My Friend</p>
                    <div class="last-message text-black-50">
                        <small>last seen at yesterday 22:25</small>
                    </div>
                </div>

                <a href="#" title="Search">
                    <i class="fas fa-search text-secondary"></i>
                </a>
                <a href="#" title="Attach">
                    <i class="fas fa-paperclip text-secondary"></i>
                </a>
                <a href="#" title="Menu">
                    <i class="fas fa-ellipsis-v text-secondary"></i>
                </a>
            </header>
            <section class="dialog flex-fill">
                <div class="message me tail" data-time="15:34">Hello!</div>
                <div class="message them" data-time="15:33">How is it going?</div>
                <div class="message them tail" data-time="15:33">Hello!</div>
            </section>
            <footer class="d-flex">
                <a href="#" class="icon my-auto mx-4">
                    <i class="far fa-grin text-secondary my-auto"></i>
                </a>
                <div class="form-group my-auto flex-fill">
                    <label for="message" class="sr-only">Message</label>
                    <input type="text" class="form-control rounded-pill" id="message" placeholder="Type a message">
                </div>
                <a href="#" class="icon my-auto mx-4">
                    <i class="fas fa-microphone text-secondary my-auto"></i>
                </a>
            </footer>
        </section>
    </main>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</html>