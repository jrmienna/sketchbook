from django.forms import ModelForm, TextInput, Textarea
from startnach.gossip.models import Gossip

class GossipForm(ModelForm):
    class Meta:
        model = Gossip
        fields = ['title', 'description',]
        widgets = {
            'title': TextInput(attrs={'placeholder': 'Spenstig tittel goes here'}),
            'description': Textarea(
            attrs={'placeholder': 'Enter juicy sladder here'}),
        }
