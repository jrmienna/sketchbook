from django.shortcuts import render
from django.http import HttpResponseRedirect

from startnach.gossip.models import Gossip
from startnach.members.models import Member

from startnach.gossip.forms import GossipForm

def home(request):
    if request.method == 'POST':
        form = GossipForm(request.POST)
        if form.is_valid():
            form.save()
            return HttpResponseRedirect('/')
    else:
        form = GossipForm()
    return render(
        request,
        'index.html',
        {   
            'members': Member.objects.all().order_by('points').reverse(),
            'gossip': Gossip.objects.all().order_by('date').reverse()[:5],
            'form': form,
        },
    )
