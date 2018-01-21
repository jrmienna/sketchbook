from django.conf import settings
from django.conf.urls import patterns, include, url
from django.conf.urls.static import static
from django.contrib import admin

from startnach.gossip.models import Gossip
from django.views.generic.list import ListView

urlpatterns = patterns('',
    # Examples:
    url(r'^$', 'startnach.views.home', name='home'),
    # url(r'^blog/', include('blog.urls')),
    url(r'archive/', ListView.as_view(model=Gossip, template_name='archive.html')),
    url(r'^admin/', include(admin.site.urls)),
)

urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
